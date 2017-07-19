package org.endeavour.databasepatcher.endpoints;

import org.endeavourhealth.databasepatcher.core.database.patchrunner.RunPatch;
import org.endeavourhealth.coreui.endpoints.AbstractEndpoint;
import org.endeavourhealth.databasepatcher.core.database.json.JsonDb;
import org.endeavourhealth.databasepatcher.core.database.json.JsonPatch;
import org.endeavourhealth.databasepatcher.core.database.models.PatchEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Path("/patch")
public final class PatchEndpoint extends AbstractEndpoint {
    private static final Logger LOG = LoggerFactory.getLogger(PatchEndpoint.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/getPatches")
    public Response getPatches(@Context SecurityContext sc) throws Exception {
        super.setLogbackMarkers(sc);

        List<Object[]> patches = PatchEntity.getPatches();

        List<JsonPatch> results = new ArrayList<>();

        for (Object[] patchEntity : patches) {
            String id = patchEntity[0] == null ? "" : patchEntity[0].toString();
            String patchDescription = patchEntity[1] == null ? "" : patchEntity[1].toString();
            String createdTimestamp = patchEntity[2] == null ? "" : patchEntity[2].toString();
            String patchScript = patchEntity[3] == null ? "" : patchEntity[3].toString();
           
            JsonPatch patch = new JsonPatch();
            patch.setId(id);
            patch.setPatchDescription(patchDescription);
            patch.setCreatedTimestamp(createdTimestamp);
            patch.setPatchScript(patchScript);

            results.add(patch);
        }

        clearLogbackMarkers();

        return Response
                .ok()
                .entity(results)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/savePatch")
    public Response savePatch(@Context SecurityContext sc, JsonPatch patch) throws Exception {
        super.setLogbackMarkers(sc);

        PatchEntity patchEntity = new PatchEntity();
        if (!patch.getId().equals(""))
            patchEntity.setId(Integer.parseInt(patch.getId()));
        patchEntity.setPatchDescription(patch.getPatchDescription());
        patchEntity.setPatchScript(patch.getPatchScript());

        String createdTimestamp = patch.getCreatedTimestamp();
        if (!createdTimestamp.equals("")) {
            if (createdTimestamp.length()<17)
                createdTimestamp+=":00";
            createdTimestamp = createdTimestamp.replaceAll("T"," ");
            patchEntity.setCreatedTimestamp(Timestamp.valueOf(createdTimestamp));
        }

        PatchEntity.savePatch(patchEntity);

        clearLogbackMarkers();

        return Response
                .ok()
                .entity(null)
                .build();
    }

    public void runPatch(JsonDb database) throws Exception {

        String patchId = database.getPatchId();
        String dbId = database.getId();

        List<Object[]> patch = PatchEntity.getPatch(Integer.parseInt(patchId));

        for (Object[] patchEntity : patch) {
            String id = patchEntity[0] == null ? "" : patchEntity[0].toString();
            String patchDescription = patchEntity[1] == null ? "" : patchEntity[1].toString();
            String createdTimestamp = patchEntity[2] == null ? "" : patchEntity[2].toString();
            String patchScript = patchEntity[3] == null ? "" : patchEntity[3].toString();

            RunPatch runPatch = new RunPatch();
            runPatch.runPatch(database, patchScript);
        }
    }



}
