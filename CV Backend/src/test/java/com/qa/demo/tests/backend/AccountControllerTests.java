package com.qa.demo.tests.backend;

import static org.junit.Assert.assertEquals;

import org.bson.BsonBinarySubType;
import org.bson.BsonValue;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.client.result.UpdateResult;
import com.qa.demo.Interoperability.AccountController;
import com.qa.demo.domain.Account;
import com.qa.demo.repository.AccountRepository;
import com.relevantcodes.extentreports.LogStatus;

@RunWith(SpringRunner.class)
public class AccountControllerTests extends BackendBaseTest
{
	@Mock
	private AccountRepository accountRepositoryMock; //STOP MOCKING ME JUNIT
	
	@Mock
	private PasswordEncoder bCodeMock;
	
	@Mock
	private MongoTemplate mongoTemplateMock;
	
	@InjectMocks
	private AccountController accountController;
	
	@Test
    public void GettingAllAccounts()
    {
        test = report.startTest("Get all accounts");        
        test.log(LogStatus.INFO, "Connecting to host");
        List<Account> mockUserList = new ArrayList<Account>();
        mockUserList.add(new Account("John", "Doe"));
        
		when(accountRepositoryMock.findAll()).thenReturn(mockUserList);
		
		assertEquals(accountController.getAllAccounts().size(), 1);		
    }
	
	@Test
	public void CreateAccountTest()
	{
		Account account = new Account("John", "Doe");
		test = report.startTest("Get all accounts");
		test.log(LogStatus.INFO, "Creating account " + account);
		
		when(accountRepositoryMock.save(account)).thenReturn(account);
		when(bCodeMock.encode(account.getPassword())).thenReturn("encryptedPassword");
		
		assertEquals(accountController.createAccount(account), account);
	}
	
	@Test
	public void AddFileToUserTest()
	{
		Account testAccount = new Account("John", "Doe");
		ObjectId testObjId = new ObjectId();
		
		Path path = Paths.get("CV.pdf");
		String name = "file.txt";
		String originalFileName = "file.txt";
		String contentType = "pdf";
		byte[] content = null;
		try {
		    content = Files.readAllBytes(path);
		} catch (final IOException e) {
		}
		MultipartFile result = new MockMultipartFile(name,
		                     originalFileName, contentType, content);
		
		when(accountRepositoryMock.findBy_id(testObjId)).thenReturn(testAccount);
		
		try {
			when(mongoTemplateMock.upsert(Query.query(where("_id").is(testObjId)), Update.update("file",new Binary(BsonBinarySubType.BINARY, result.getBytes())), Account.class)).thenReturn(new UpdateResult() {
				
				@Override
				public boolean wasAcknowledged() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean isModifiedCountAvailable() {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public BsonValue getUpsertedId() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public long getModifiedCount() {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public long getMatchedCount() {
					// TODO Auto-generated method stub
					return 0;
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(accountController.addFileToUser(result, testObjId), "File addition successful to account: " + testAccount.getFirstName()+ " " + testAccount.getLastName());
	}
}
