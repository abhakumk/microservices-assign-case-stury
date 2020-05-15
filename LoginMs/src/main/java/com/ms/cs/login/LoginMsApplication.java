package com.ms.cs.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author w2cluster03
 *
 */
@SpringBootApplication
@EnableSwagger2
public class LoginMsApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(LoginMsApplication.class, args);
	}
	
//	@Bean
//	CommandLineRunner dlr(ApplicationContext ctx) {
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//		MultiValueMap<String, String> form =
//				 new LinkedMultiValueMap<String, String>();
//		form.add("grant_type", "urn:ibm:params:oauth:grant-type:apikey"); 
//		form.add("apikey", "01WOKe8MNYOPSpd_F47ntPAMyY1aSApRVK6slb_hxIHR");
//        
//		HttpEntity request = new HttpEntity<>(form, headers);
//        String url = "https://iam.cloud.ibm.com/oidc/token";
////        RestTemplate restTemplate = (new RestTemplateBuilder()).build();
//        ResponseEntity<?> responseEntity = new RestTemplate().postForEntity(url, request, String.class);
//        String response = "{\"ServiceResponse\":"+responseEntity.getBody()+"}";
//        ServiceResponse entityResponse = (ServiceResponse) CommonUtil.getJSONObject(response, ServiceResponse.class);
//		return null;
//	}
	
	

}
