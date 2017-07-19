package org.endeavour.databasepatcher.endpoints;

import org.endeavourhealth.coreui.endpoints.AbstractEndpoint;
import org.endeavourhealth.databasepatcher.core.database.json.JsonDb;
import org.endeavourhealth.databasepatcher.core.database.models.*;
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

@Path("/dashboard")
public final class DashboardEndpoint extends AbstractEndpoint {
    private static final Logger LOG = LoggerFactory.getLogger(DashboardEndpoint.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/getDatabases")
    public Response getDatabases(@Context SecurityContext sc, @QueryParam("region") String region,
                                 @QueryParam("zone") String zone,
                                 @QueryParam("type") String type) throws Exception {
        super.setLogbackMarkers(sc);

        List<Object[]> dbs = DbEntity.getDatabases(region, zone, type);

        List<JsonDb> results = new ArrayList<>();

        for (Object[] dbEntity : dbs) {
            String name = dbEntity[0] == null ? "" : dbEntity[0].toString();
            String hostname = dbEntity[1] == null ? "" : dbEntity[1].toString();
            String port = dbEntity[2] == null ? "" : dbEntity[2].toString();
            String username = dbEntity[3] == null ? "" : dbEntity[3].toString();
            String password = dbEntity[4] == null ? "" : dbEntity[4].toString();
            String lastPatchTimestamp = dbEntity[5] == null ? "" : dbEntity[5].toString();
            String lastStatus = dbEntity[6] == null ? "" : dbEntity[6].toString();
            String currentVersion = dbEntity[7] == null ? "" : dbEntity[7].toString();
            String nextPatchTimestamp = dbEntity[8] == null ? "" : dbEntity[8].toString();
            String targetVersion = dbEntity[9] == null ? "" : dbEntity[9].toString();
            String region_name = dbEntity[10] == null ? "" : dbEntity[10].toString();
            String zone_name = dbEntity[11] == null ? "" : dbEntity[11].toString();
            String type_name = dbEntity[12] == null ? "" : dbEntity[12].toString();
            String regionId = dbEntity[13] == null ? "" : dbEntity[13].toString();
            String zoneId = dbEntity[14] == null ? "" : dbEntity[14].toString();
            String typeId = dbEntity[15] == null ? "" : dbEntity[15].toString();
            String id = dbEntity[16] == null ? "" : dbEntity[16].toString();
            String patchId = dbEntity[17] == null ? "" : dbEntity[17].toString();


            JsonDb db = new JsonDb();
            db.setName(name);
            db.setHostname(hostname);
            db.setPort(port);
            db.setUsername(username);
            db.setPassword(password);
            db.setLastPatchTimestamp(lastPatchTimestamp);
            db.setLastStatus(lastStatus);
            db.setCurrentVersion(currentVersion);
            db.setNextPatchTimestamp(nextPatchTimestamp);
            db.setTargetVersion(targetVersion);
            db.setRegion(region_name);
            db.setZone(zone_name);
            db.setType(type_name);
            db.setRegionId(regionId);
            db.setAvailabilityZoneId(zoneId);
            db.setDataTypeId(typeId);
            db.setId(id);
            db.setPatchId(patchId);

            results.add(db);
        }

        clearLogbackMarkers();

        return Response
                .ok()
                .entity(results)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/getRegions")
    public Response getRegions(@Context SecurityContext sc) throws Exception {
        super.setLogbackMarkers(sc);

        List<RegionEntity> results = RegionEntity.getRegions();

        clearLogbackMarkers();

        return Response
                .ok()
                .entity(results)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/getZones")
    public Response getZones(@Context SecurityContext sc) throws Exception {
        super.setLogbackMarkers(sc);

        List<AvailabilityZoneEntity> results = AvailabilityZoneEntity.getZones();

        clearLogbackMarkers();

        return Response
                .ok()
                .entity(results)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/getDataTypes")
    public Response getDataTypes(@Context SecurityContext sc) throws Exception {
        super.setLogbackMarkers(sc);

        List<DataTypesEntity> results = DataTypesEntity.getDataTypes();

        clearLogbackMarkers();

        return Response
                .ok()
                .entity(results)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/saveDatabase")
    public Response saveDatabase(@Context SecurityContext sc, JsonDb database) throws Exception {
        super.setLogbackMarkers(sc);

        DbEntity dbEntity = new DbEntity();
        if (!database.getId().equals(""))
            dbEntity.setId(Integer.parseInt(database.getId()));
        dbEntity.setName(database.getName());
        dbEntity.setHostname(database.getHostname());
        dbEntity.setPort(database.getPort());
        dbEntity.setUsername(database.getUsername());
        dbEntity.setPassword(database.getPassword());
        String lastPatchTime = database.getLastPatchTimestamp();
        if (!lastPatchTime.equals("")) {
            if (lastPatchTime.length()<17)
                lastPatchTime+=":00";
            lastPatchTime = lastPatchTime.replaceAll("T"," ");
            dbEntity.setLastPatchTimestamp(Timestamp.valueOf(lastPatchTime));
        }
        if (!database.getLastStatus().equals(""))
            dbEntity.setLastStatus(Byte.parseByte(database.getLastStatus()));
        dbEntity.setCurrentVersion(database.getCurrentVersion());
        String nextPatchTime = database.getNextPatchTimestamp();
        if (!nextPatchTime.equals("")) {
            if (nextPatchTime.length()<17)
                nextPatchTime+=":00";
            nextPatchTime = nextPatchTime.replaceAll("T"," ");
            dbEntity.setNextPatchTimestamp(Timestamp.valueOf(nextPatchTime));
        }
        dbEntity.setTargetVersion(database.getTargetVersion());
        dbEntity.setRegionId(Integer.parseInt(database.getRegionId()));
        dbEntity.setAvailabilityZoneId(Integer.parseInt(database.getAvailabilityZoneId()));
        dbEntity.setDataTypeId(Integer.parseInt(database.getDataTypeId()));
        dbEntity.setPatchId(Integer.parseInt(database.getPatchId()));

        DbEntity.saveDatabase(dbEntity);

        if (database.getRunNow()!=null && database.getRunNow().equals("true")) {
            PatchEndpoint patchEndpoint = new PatchEndpoint();
            patchEndpoint.runPatch(database);

        }

        clearLogbackMarkers();

        return Response
                .ok()
                .entity(null)
                .build();
    }





}
