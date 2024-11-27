import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	private final RestTemplate restTemplate;
	
	public ApiController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@GetMapping(/consume)
	public ResponseEntity<String> consumeApi() {
		String apiUrl = "";
		String response = restTemplate.getForObject(apiUrl, String.class);
		return ResponseEntity.ok(response);
	}
}
