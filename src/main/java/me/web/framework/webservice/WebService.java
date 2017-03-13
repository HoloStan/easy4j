package me.web.framework.webservice;

public interface WebService {
	/* 注释（Annotation）：在 javax.ws.rs.* 中定义，是 JAX-RS (JSR 311) 规范的一部分。 
	   @Path：定义资源基 URI。由上下文根和主机名组成，资源标识符类似于 http://localhost:8080/RESTful/rest/hello。 
	   @GET @POST @PUT @DELETE：这意味着以下方法可以响应 的HTTP方法。 
	   @Produces：以纯文本方式定义响应内容 MIME 类型。
	   
	   @Context： 使用该注释注入上下文对象，比如 Request、Response、UriInfo、ServletContext 等。 
	   @Path("{contact}")：这是 @Path 注释，与根路径 “/contacts” 结合形成子资源的 URI。 
	   @PathParam("contact")：该注释将参数注入方法参数的路径。其他可用的注释有 @FormParam、@QueryParam 等。 
	   @Produces：响应支持多个 MIME 类型。在本例和上一个示例中，APPLICATION/XML 将是默认的 MIME 类型。
	*/
//	@GET   
//    @Produces (MediaType.TEXT_PLAIN)
//    @Path("/html")   
//    public String getHTMLData(@PathParam("name")String name);
//	
//	@GET   
//    @Produces ({MediaType.APPLICATION_JSON})   
//    @Path("/json")   
//    public List<User> getJSONData();  
}
