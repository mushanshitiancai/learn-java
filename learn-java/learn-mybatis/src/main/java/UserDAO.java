import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by mazhibin on 17/3/12
 */
public class UserDAO {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, ExecutionException, InterruptedException {
        List<String> urls = new ArrayList<>();
        List<Long> userIds = new ArrayList<>();

        FileReader fileReader = new FileReader("userIds.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (NumberUtils.isDigits(line)) {
                userIds.add(Long.valueOf(line));
            }
        }

        UserDAO userDAO = new UserDAO();
        userDAO.connect(urls);

        userDAO.getAllUserBalance(userIds);
    }

    private static final int DB_COUNT = 10;
    private List<Connection> connections = new ArrayList<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(DB_COUNT);

    private void connect(List<String> urls) throws ClassNotFoundException, SQLException {
        if (CollectionUtils.isEmpty(urls) || urls.size() != DB_COUNT) {
            throw new IllegalArgumentException();
        }

        Class.forName("com.mysql.jdbc.Driver");

        for (String url : urls) {
            Connection connection = DriverManager.getConnection(url);
            connections.add(connection);
        }
    }

    public double getAllUserBalance(List<Long> userIds) throws InterruptedException, ExecutionException {
        double result = 0;
        Map<Integer, List<Long>> userIdsByDBIndex = userIds.stream().collect(Collectors.groupingBy(this::getDBIndex));

        List<Callable<Double>> tasks = new ArrayList<>();
        for (Map.Entry<Integer, List<Long>> userIdEntry : userIdsByDBIndex.entrySet()) {
            tasks.add(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    return getAllUserBalance(userIdEntry.getValue(), userIdEntry.getKey());
                }
            });
        }

        List<Future<Double>> futures = executorService.invokeAll(tasks);

        for (Future<Double> future : futures) {
            result += future.get();
        }

        return result;
    }

    private double getAllUserBalance(List<Long> userIds, int dbIndex) throws SQLException {
        double result = 0;
        Connection connection = connections.get(dbIndex);

        List<String> userIdStrings = userIds.stream().map(Object::toString).collect(Collectors.toList());
        String sql = String.format("select sum(balance) from user_account where userId in (%s)", String.join(",", userIdStrings));
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            result = resultSet.getDouble(1);
        }

        return result;
    }

    private int getDBIndex(long userId) {
        return (int) (userId >> 46);
    }
}
