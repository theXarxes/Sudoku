package sudoku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    private static Logger logger = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);
    Connection connection;
    Statement statement;
    private String name;
    private final String user = "root";
    private final String pass = "root";
    public final String sd = "Sudoku";

    private String filename;

    JdbcSudokuBoardDao(String filename) {
        this.filename = filename;
    }


    public void connect(String url) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(url,user,pass);
        logger.info("Connected");
    }

    @Override
    public SudokuBoard read() throws JdbcException, SQLException, ClassNotFoundException {
        String url = "jdbc:sqlite:" + filename;
        connect(url);
        String select = "select name1,fields from "
                + sd + " where name1=?";
        ResultSet data;
        String board;
        PreparedStatement preparedStatement = connection.prepareStatement(select);
        preparedStatement.setString(1, filename);
        data = preparedStatement.executeQuery();
        board = data.getString(2);
        SimpleSudokuSolver solver = new SimpleSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        sudokuBoard.createSudokuBoard(board);
        connection.close();
        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard object)
            throws DaoException, SQLException, ClassNotFoundException {
        String url = "jdbc:sqlite:" + filename;
        connect(url);
        String sql = "create table " + sd
                + "(name1 varchar(20) primary key not null, "
                + "fields varchar(81))";

        String insertData = "insert into Sudoku(name1,fields) values (?,?)";
        statement = connection.createStatement();
        statement.execute(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(insertData);
            preparedStatement.setString(1, filename);
            preparedStatement.setString(2, object.toString1());
        preparedStatement.executeUpdate();
        connection.close();
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
