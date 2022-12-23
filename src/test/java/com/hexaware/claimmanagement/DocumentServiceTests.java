package com.hexaware.claimmanagement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.claimmanagement.Entity.Claim;
import com.hexaware.claimmanagement.Entity.Document;
import com.hexaware.claimmanagement.Entity.Nominee;
import com.hexaware.claimmanagement.Entity.Policy;
import com.hexaware.claimmanagement.Entity.Role;
import com.hexaware.claimmanagement.Entity.User;
import com.hexaware.claimmanagement.Repository.ClaimRepository;
import com.hexaware.claimmanagement.Repository.DocumentRepository;
import com.hexaware.claimmanagement.Service.DocumentService;
import com.hexaware.claimmanagement.Service.DocumentServiceImpl;
import static org.mockito.ArgumentMatchers.*;


@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DocumentServiceTests {
	
	@Mock
	ClaimRepository claimRepo;
	
	@Mock
	DocumentRepository docRepo;
	
	@InjectMocks
	DocumentService docServ =new DocumentServiceImpl();
	
	@Before
	public void initializeMockito() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSaveDocument_Success() {
		//A Multipart file array will be sent and stored for a claim.
		
		Path path = Paths.get("C:/Users/1000071024/Documents/workspace-spring-tool-suite-4-4.16.0.RELEASE/claimmanagement/testingDocument1");
		String name = "testingDocument.txt";
		String originalFileName = "testingDocument.txt";
		String contentType = "text/plain";
		byte[] content = null;
		try {
		    content = Files.readAllBytes(path);
		} catch (final IOException e) {
		}
		MultipartFile uploadingDoc = new MockMultipartFile(name,
		                     originalFileName, contentType, content);
		
		MultipartFile[] docList = new MultipartFile[] {uploadingDoc};
		
		Claim claim =new Claim();
		
		when(claimRepo.findById(anyInt())).thenReturn(Optional.of(claim));
		
		ResponseEntity<?> results=docServ.saveDocument(1, docList);
		assertEquals(HttpStatus.OK,results.getStatusCode());
	}
	
	@Test
	public void testSaveDocument_Failure() {
		//Document will throw an IOException as we are passing null input file.
		
		Claim claim =new Claim();
	
		when(claimRepo.findById(anyInt())).thenReturn(Optional.of(claim));
		
		ResponseEntity<?> results=docServ.saveDocument(1, null);
		assertEquals(HttpStatus.OK,results.getStatusCode());
	}
	
	@Test
	public void testGetFiles_Success() {
		//A list of Documents for a particular Claim will be returned with status 200 OK.
		
		Path path1 = Paths.get("C:/Users/1000071024/Documents/workspace-spring-tool-suite-4-4.16.0.RELEASE/claimmanagement/testingDocument1");
		String name1 = "testingDocument1.txt";
		String originalFileName1 = "testingDocument1.txt";
		String contentType1 = "text/plain";
		byte[] content1 = null;
		try {
		    content1 = Files.readAllBytes(path1);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		
		Path path2 = Paths.get("C:/Users/1000071024/Documents/workspace-spring-tool-suite-4-4.16.0.RELEASE/claimmanagement/testingDocument2");
		String name2 = "testingDocument2.txt";
		String originalFileName2 = "testingDocument2.txt";
		String contentType2 = "text/plain";
		byte[] content2 = null;
		try {
		    content2 = Files.readAllBytes(path2);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		MultipartFile uploadingDoc1 = new MockMultipartFile(name1,
		                     originalFileName1, contentType1, content1);
		
		MultipartFile uploadingDoc2 = new MockMultipartFile(name2,
                originalFileName2, contentType2, content2);
		
		MultipartFile[] docList = new MultipartFile[] {uploadingDoc1,uploadingDoc2};
		
		Claim claim =new Claim();
		
		
		
		when(claimRepo.findById(2)).thenReturn(Optional.of(claim));
		docServ.saveDocument(2, docList);
		
		List<Document> results=docServ.getFiles(2);
		assertEquals(2,results.size());
		assertEquals("testingDocument2.txt",results.get(1).getDocname());
	}
	
	@Test
	public void testGetFiles_Failure() {
		//A null list will be returned as there are no documents presenet against the claim.
		
		Claim claim =new Claim();
		when(claimRepo.findById(anyInt())).thenReturn(Optional.of(claim));
		
		List<Document> results=docServ.getFiles(2);
		assertEquals(2,results.size());
	}
	
	@Test
	public void testGetFileById_Success() {
		//Document will be found for a document ID.
		
		Claim claim =new Claim();
		Document doc=new Document();
		when(docRepo.getFileById(anyInt())).thenReturn(Optional.of(doc));
		
		Document results=docServ.getFileById(2);
		assertEquals(doc,results);
	}
	
	@Test
	public void testGetFileById_Failure() {
		//Document is absent for an Id, Hence it will give a 404 Not Found response.
		
		Claim claim =new Claim();
		Document doc=new Document();
		when(docRepo.getFileById(1)).thenReturn(Optional.of(doc));
		
		Document results=docServ.getFileById(2);
		assertEquals(doc,results);
	}
	
	
	

}
