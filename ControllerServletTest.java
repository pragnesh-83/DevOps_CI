import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cg.ControllerServlet;
import com.cg.dao.CarDAO;
import com.cg.dto.CarDTO;

public class ControllerServletTest {

	
	@Mock
	private CarDAO carDao;
	
	@InjectMocks
	private ControllerServlet myServlet;
	
	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
		
		// Mock CarDAO
		//carDao = Mockito.mock(CarDAO.class);
		
		System.out.println(carDao.getClass());
	}
	
	@Test
	public void testProcessRequest() throws ServletException, IOException {
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		
		// WHEN- THEN Pattern
		Mockito.when(request.getParameter("action")).thenReturn("viewCarList");
				
		// DATA FIXTURE
		List<CarDTO> cars = new LinkedList<>();
		
		CarDTO car = new CarDTO();
		car.setId(1);
		car.setMake("Honda");
		car.setModel("City");
		car.setModelYear("2015");
		cars.add(car);
		
		// WHEN- THEN Pattern
		Mockito.when(carDao.findAll()).thenReturn(cars);
		
		myServlet.setCarDAO(carDao);
		
		//EXECUTION		
		myServlet.processRequest(request, response);
		
		///VERIFICATION
		Mockito.verify(request).getParameter("action");
		Mockito.verify(carDao).findAll();
	}
	
	
}
