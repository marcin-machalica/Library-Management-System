package machalica.marcin.spring.lms.exception;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String resourceClass, long id) {
		super("Could not find resource: " + resourceClass + " with id: " + id);
	}
	
	public ResourceNotFoundException(String resourceClass) {
		super("Could not find requested resource of class " + resourceClass);
	}
}
