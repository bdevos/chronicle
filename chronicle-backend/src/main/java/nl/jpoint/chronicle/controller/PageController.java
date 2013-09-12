package nl.jpoint.chronicle.controller;

import com.google.inject.Inject;
import nl.jpoint.chronicle.dao.PageDAO;
import nl.jpoint.chronicle.domain.Page;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Date;

@Singleton
@Path("/page")
public class PageController {

    @Inject
    private ObjectMapper mapper;

    @Inject
    private PageDAO pageDAO;

    @POST
    public String postPage(Page page) throws IOException {
        pageDAO.save(page);
        return formatPage(page);
    }


    @GET
    @Path("/{pageName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPage(@PathParam("pageName") String pageName) throws IOException {
        String uri = parsePageUri(pageName);
        Page page = pageDAO.findByUri(uri);

        if (page == null) {
            page = new Page();
            page.setUri(uri);
            page.setParent(parseParentUri(pageName));
            page.getMeta().setCreated((new Date().getTime()));

            if ("main".equals(uri)) {
                //createContentForDefaultPage(page);
            } else {
                page.setTitle("New page: " + uri);
            }
        }

        return formatPage(page);

    }

    private String formatPage(Page page) throws IOException {
        return mapper.writeValueAsString(page);
    }

    public static String parsePageUri(String target) {
        String[] uriParts = target.split("/");

        StringBuilder uri = new StringBuilder();
        String validPieceOfUri = null;

        for (int i = 0; i < uriParts.length; i++) {
            if (i > 1 && uriParts[i].length() > 0) {
                validPieceOfUri = uriParts[i];
            }
            if (validPieceOfUri != null) {
                if (uri.length() > 0) {
                    uri.append("/");
                }
                uri.append(validPieceOfUri);
                validPieceOfUri = null;
            }
        }
        return uri.toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPages() throws IOException {
        return mapper.writeValueAsString(pageDAO.query());

    }

    @DELETE
    public void deletePage(Page page) {
        pageDAO.delete(page);
    }

    public static boolean isPageRequest(String target) {
        return target.startsWith("/page");
    }


    public static String parseParentUri(String target) {
        String uri = parsePageUri(target);

        if (!uri.contains("/")) {
            return "";
        }
        return uri.substring(0, uri.lastIndexOf('/'));
    }
}
