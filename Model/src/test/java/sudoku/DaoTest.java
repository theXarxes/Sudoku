package sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaoTest {
        private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        SimpleSudokuSolver s =new SimpleSudokuSolver();
        private SudokuBoard sudokuBoard = new SudokuBoard(s);
        Logger logger = LoggerFactory.getLogger(DaoTest.class);
        private Dao<SudokuBoard> file;
        private SudokuBoard board;
        FileSudokuBoardDao dadao=new FileSudokuBoardDao("SzymekSlaboGrawLoLa.exe");

        @BeforeEach
        public void testPreparation() {
            System.setProperty("log4j.configurationFile","log4j.properties");
        }

        @Test
        public void notAllTest() {
            file = factory.getFileDao("txt");
            try {
                file.write(sudokuBoard);
            } catch (IOException | SQLException | ClassNotFoundException e) {
                logger.debug("1");
            }

            try {
                board = file.read();
            } catch (IOException e) {
                logger.debug("2");
            } catch (ClassNotFoundException e) {
                logger.debug("3");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            assertEquals(dadao.getName(),"SzymekSlaboGrawLoLa.exe");
            assertEquals(sudokuBoard, board);
            try {
                dadao.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Test
        void JdbcSudokuReadTest(){
            try {
                Dao<SudokuBoard> jdbcSudokuBoardDao;
                SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
                jdbcSudokuBoardDao = factory.getDatabaseDao("Tekst");
                SudokuBoard sudokuBoardFromFile = jdbcSudokuBoardDao.read();
                logger.info("Read opperation succeeded");
            }catch (JdbcException e){
                logger.info("Cannot load file");
            }catch (Exception e){
                logger.info("Cannot load file");
            }
        }

        @Test
        void JdbcSudokuWriteTest(){
            try {
                Dao<SudokuBoard> fileSudokuBoardDao;
                fileSudokuBoardDao = factory.getDatabaseDao("Tekst");
                fileSudokuBoardDao.write(sudokuBoard);
                logger.info("Write opperation succeeded");
            }catch (JdbcException e){
                logger.info("Cannot load file");
            }catch (Exception e){
                logger.info("Cannot load file");
            }

        }
    }

