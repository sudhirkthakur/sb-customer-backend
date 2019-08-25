package com.sb.demo.customer.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;



import com.sb.demo.customer.exceptions.AppException;
import com.sb.demo.customer.models.responses.AppExceptionResponse;
import com.sb.demo.customer.utils.Constants;
import com.sb.demo.customer.utils.ErrorUtil;
import com.sb.demo.customer.utils.MessageService;


@RunWith(MockitoJUnitRunner.class)
public class ErrorControllerTest {
	
    private WebRequest webRequest = mock(WebRequest.class);
    
    private final String userMessage = "message"; 
    private final String errorCode = "code"; 
    private final String timestamp = "timestamp"; 
    
    private String className; 
    private String fileName; 
    
    private String userMsgPersistenceExcepion; 
    private String debugMsgPersistenceExcepion; 
 
    private String userMsgLoggingExcepion; 
    private String debugMsgLoggingExcepion; 
    private String userMsgAppExcepion; 
    private String debugMsgAppExcepion; 

    @InjectMocks
    private ErrorController controller = new ErrorController();
  
    @Mock
    private MessageService messageService;
    @Mock
    private ErrorUtil errorUtil; 
    @Mock
    MethodArgumentNotValidException e;
    @Mock
    FieldError fieldError = new FieldError("name", "field", null, true, new String[] { "test-code" },
				null, "message");
    
    @Before
    public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
        
        className= "com.ibm.gtd.document.bucket.controllers.ErrorControllerTest"; 
        fileName = "ErrorControllerTest.java";
        
        userMsgPersistenceExcepion="Persistence exception occurred."; 
        debugMsgPersistenceExcepion="Persistence exception debug message."; 
        userMsgLoggingExcepion="Logging exception occurred."; 
        debugMsgLoggingExcepion="Logging exception debug message."; 
        userMsgAppExcepion="App exception occurred."; 
        debugMsgAppExcepion="App exception debug message."; 
    }
    
    
    @Test
	public void handleAppException() throws Exception {
    	final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		Exception sourceException=new Exception("");
		AppException appException=new AppException(sourceException,Constants.APP_EXCEPTION);
	
		when(messageService.getMessage(appException.getErrorCode(),appException.getUserMessageParams())).thenReturn(userMsgAppExcepion);
//		when(messageService.getDebugMessage(appException.getErrorCode(),appException.getDebugMessageParams())).thenReturn(debugMsgAppExcepion);
		when(errorUtil.getHttpStatus(appException.getErrorCode())).thenReturn(HttpStatus.BAD_REQUEST);

    	ResponseEntity<AppExceptionResponse> response = controller.handleAppException(appException, webRequest);

		assertEquals(AppExceptionResponse.class, response.getBody().getClass());
		
		assertTrue(response.getBody().toString().contains(userMessage));
		assertTrue(response.getBody().toString().contains(errorCode));
		assertTrue(response.getBody().toString().contains(timestamp));

//		assertTrue(outContent.toString().contains(userMessage));
		assertTrue(outContent.toString().contains(errorCode));
//		assertTrue(outContent.toString().contains(timestamp));
//		assertTrue(outContent.toString().contains(className));
//		assertTrue(outContent.toString().contains(fileName));
	}
    
    
	@Test
	public void handleException() throws Exception {
		Exception e = new Exception("Exception Occured!");
		ResponseEntity<AppExceptionResponse> response = controller.handleUnknownException(e, webRequest);
		assertEquals(AppExceptionResponse.class, response.getBody().getClass());
		assertEquals(INTERNAL_SERVER_ERROR, response.getStatusCode());
	}


}
