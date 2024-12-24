import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import com.google.gson.Gson;

public class SudokuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SudokuGenerator generator = new SudokuGenerator();
        int[][] sudokuGrid = generator.getGrid();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Convert the grid to JSON and write the response
        String json = new Gson().toJson(sudokuGrid);
        response.getWriter().write(json);
    }
}
