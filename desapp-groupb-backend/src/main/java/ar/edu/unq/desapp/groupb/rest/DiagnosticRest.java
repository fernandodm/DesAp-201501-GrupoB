/**
 * 
 */
package ar.edu.unq.desapp.groupb.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ar.edu.unq.desapp.groupb.repositories.DiagnosticDAO;

@Path("/diagnoses")
public class DiagnosticRest {
    
    private DiagnosticDAO diagnosticDAO;

	public DiagnosticDAO getDiagnosticDAO() {
		return diagnosticDAO;
	}

	public void setDiagnosticDAO(DiagnosticDAO diagnosticDAO) {
		this.diagnosticDAO = diagnosticDAO;
	}

    
    
    
//    @GET
//    @Path("/{from}")
//    @Produces("application/json")
//    public List<Post> findPostsPublishedByBlogId(@PathParam("from") final Integer from) {
//        List<Post> posts = postDAO.getPosts(from, NUMBER_OF_POST, "");
//        return posts;
//    }

//    @GET
//    @Path("/byAuthor/{id}")
//    @Produces("application/json")
//    public List<Post> findPostsPublishedByAuthorId(@PathParam("id") final String id) {
//        List<Post> posts = postDAO.getPosts(id);
//        return posts;
//    }
//
    @GET
    @Path("/count")
    @Produces("application/json")
    public Integer countDiagnosesAmount() {
//        return diagnosticDAO.count();
    	return 1;
    }
//
//    @GET
//    @Path("/tags")
//    @Produces("application/json")
//    public Set<String> getTagsByBlogId() {
//        return postDAO.getTags();
//    }
//
//
//
//    public void setPostDAO(final PostRepository postDAO) {
//        this.postDAO = postDAO;
//    }
}
