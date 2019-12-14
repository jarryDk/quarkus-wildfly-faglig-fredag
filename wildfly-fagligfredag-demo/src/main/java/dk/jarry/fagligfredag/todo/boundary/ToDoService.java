package dk.jarry.fagligfredag.todo.boundary;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.Principal;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import dk.jarry.fagligfredag.todo.entity.ToDo;

@Stateless
public class ToDoService {

	@PersistenceContext( //
			unitName = "ToDoPU", // 
			type = PersistenceContextType.TRANSACTION)
	EntityManager entityManager;

	@Inject
	Principal principal;
	
	public ToDoService() {
	}

	@Transactional
	public ToDo create(ToDo toDo) {

		if (toDo.getId() != null) {
			throw new WebApplicationException("ToDo not valid.", Response.Status.BAD_REQUEST);
		}
		
		String user = principal.getName();
		toDo.setCreateBy(user);
						
		toDo.setCreatedDate(Calendar.getInstance().getTime());
		toDo.setUpdatedDate(Calendar.getInstance().getTime());
		
		entityManager.persist(toDo);
		entityManager.flush();
		entityManager.refresh(toDo);

		return toDo;
	}

	@Transactional
	public ToDo read(Object id) {
		ToDo toDo = entityManager.find(ToDo.class, id);
		if (toDo != null) {
			return toDo;
		} else {
			throw new WebApplicationException("ToDo with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
		}
	}

	@Transactional
	public ToDo update(Integer id, ToDo toDo) {
		
		if(toDo.getId() == null) {
			toDo.setId(id);	
		}	
		
		if (read(id) != null) {
			
			String user = principal.getName();
			toDo.setUpdatedBy(user);
			
			toDo.setUpdatedDate(Calendar.getInstance().getTime());
			
			ToDo merge = entityManager.merge(toDo);
			return merge;
		} else {
			throw new WebApplicationException("ToDo with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
		}
	}

	@Transactional
	public void delete(Object id) {

		ToDo toDo = read(id);

		if (toDo != null) {
			entityManager.remove(toDo);
		} else {
			throw new WebApplicationException("ToDo with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
		}
	}

	@Transactional
	public List<ToDo> list(Long from, Long limit) {
		return entityManager.createNamedQuery("ToDos.findAll", ToDo.class).getResultList();
	}

	@Provider
	public static class ErrorMapper implements ExceptionMapper<Exception> {

		@Override
		public Response toResponse(Exception exception) {
			
			int code = 500;
			
			if (exception instanceof WebApplicationException) {
				code = ((WebApplicationException) exception).getResponse().getStatus();
			}
			
			if (exception instanceof javax.ejb.EJBAccessException) {
				code = Response.Status.FORBIDDEN.getStatusCode();
			}
			
			return Response.status(code)
					.entity(
							Json.createObjectBuilder() //
							.add("error", exception.getMessage()) //
							.add("stackTrace", stackTrace(exception)) //
							.add("code", code) //
							.build())
					.build();
		}
		
		String stackTrace(Exception exception) {
			StringWriter writer = new StringWriter();
            PrintWriter printWriter= new PrintWriter(writer);
            exception.printStackTrace(printWriter);
            return writer.toString();
		}

	}

}
