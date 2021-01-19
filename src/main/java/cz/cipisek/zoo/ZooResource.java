package cz.cipisek.zoo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Path("zviratko")
@Produces(MediaType.APPLICATION_JSON)
public class ZooResource {

    static HashMap<Integer, HashMap<String, String>> zviratko = new HashMap<>();
    static Integer cislo = 0;

    @GET
    public Response zobrazitZviratka(){
        return Response.ok(zviratko).build();
    }

    @POST
    public Response noveZviratko(@FormParam("nazev") String nazev, @FormParam("vek") String vek) {
        if(nazev != null && vek != null ){
            HashMap<String,String> noveZviratko = new HashMap<>();
            noveZviratko.put("nazev", nazev);
            noveZviratko.put("vek", vek);
            zviratko.put(cislo, noveZviratko);
            cislo++;
            return Response.ok(zviratko).build();
        } else {
            return Response.ok("Vlozte jmeno a vek zviratka").build();
        }
    }

    @GET
    @Path("{cislo}")
    public Response najitZviratko(@PathParam("cislo") Integer cislo) {
        if(cislo != null && zviratko.get(cislo) != null){
            return Response.ok(zviratko.get(cislo)).build();
        } else {
            return Response.ok("zviratko neexistuje").build();
        }
    }

    @DELETE
    @Path("{cislo}")
    public Response smazatZviratko(@PathParam("cislo") Integer cislo) {
        if(cislo != null){
            zviratko.remove(cislo);
        }
        return Response.ok(zviratko).build();
    }

    @PUT
    @Path("{cislo}")
    public Response zmenitZviratko(@PathParam("cislo") Integer cislo,@FormParam("nazev") String nazev ,@FormParam("vek") String vek) {
        if(zviratko.get(cislo) != null && cislo != null && nazev != null && vek != null){
            HashMap<String,String> noveZviratko = new HashMap<>();
            noveZviratko.put("nazev", nazev);
            noveZviratko.put("vek", vek);
            zviratko.put(cislo, noveZviratko);
        }
        return Response.ok(zviratko).build();
    }

    

}
