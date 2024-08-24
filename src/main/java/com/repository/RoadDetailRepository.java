package com.repository;

import com.model.GeoDetails;
import com.model.GeoRequest;
import com.model.RoadDetail;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoadDetailRepository extends MongoRepository<RoadDetail, String> {
    // Project GeoDetails obj for roads(get an obj for each road)
    String PROJECT_ROAD = "{ '$project': { '_id': 0, 'uuid': '$uuid', 'name': '$name', 'description': '$description' } }";
    @Aggregation(pipeline = {PROJECT_ROAD})
    List<GeoDetails> getRoads();


    // Project GeoDetails obj for directions (get an obj for each direction)
    String PROJECT_DIRECTIONS_ALL = "{ '$project': { '_id': 0, 'direction': '$directions'} }";  // get all arrays of directions for each road
    String UNWIND_DIRECTIONS = "{ '$unwind': { 'path': '$direction', preserveNullAndEmptyArrays: false} }"; // convert arrays of directions in objects
    String PROJECT_DIRECTIONS_OBJ = "{ '$project': { 'uuid': '$direction.uuid', 'name': '$direction.name', 'description': '$direction.description' } }";  // project the fields we want
    @Aggregation(pipeline = {PROJECT_DIRECTIONS_ALL,  UNWIND_DIRECTIONS, PROJECT_DIRECTIONS_OBJ})
    List<GeoDetails> getAllDirections();

    // Project GeoDetails obj for directions for a specific road
    String MATCH_ROAD_BY_ID = "{ '$match': { 'uuid' : {'$eq': '?0'} } }"; // get a specific road by matching id
    String PROJECT_DIRECTIONS = "{ '$project' : { '_id': 0, 'direction': '$directions'} }"; // get all arrays of directions for this road
    String UNWIND_DIRECTIONSS = "{ '$unwind': {'path': '$direction', preserveNullAndEmptyArrays: false } }"; // convert arrays of directions in objects
    String PROJECT_DIRECTIONSS_OBJ = "{ '$project': { 'uuid': '$direction.uuid', 'name': '$direction.name', 'description': '$direction.description'} }"; // project the fields we want
    @Aggregation(pipeline = {MATCH_ROAD_BY_ID, PROJECT_DIRECTIONS, UNWIND_DIRECTIONSS, PROJECT_DIRECTIONSS_OBJ})
    List<GeoDetails> getDirectionsForARoad(String id);


    // Project GeoDetails obj for sections (get an obj for each section)
    String PROJECT_DIRECTIONSS = "{ '$project': { '_id': 0, 'direction': '$directions'} }";  // get all arrays of directions for each road
    String UNWIND_DIRECTIONS_1 = "{ '$unwind': { 'path': '$direction', preserveNullAndEmptyArrays: false} }"; // convert arrays of directions in objects
    String PROJECT_SECTIONS_ALL = "{ '$project': {'section': '$direction.sections'} }";  // get all arrays of sections for each direction
    String UNWIND_SECTIONS = "{ '$unwind': { 'path': '$section', preserveNullAndEmptyArrays: false} }"; // convert arrays of sections in objects
    String PROJECT_SECTIONS_OBJ = "{ '$project': { 'uuid': '$section.uuid', 'name': '$section.name', 'description': '$section.description'} }"; // project the fields we want
    @Aggregation(pipeline = {PROJECT_DIRECTIONSS, UNWIND_DIRECTIONS_1, PROJECT_SECTIONS_ALL, UNWIND_SECTIONS, PROJECT_SECTIONS_OBJ})
    List<GeoDetails> getAllSections();



    // Project GeoDetails obj for sections (get an obj for a specific road)
    String MATCH_ROAD_BY_ID_1 = "{ '$match': { 'uuid' : {'$eq': '?0'} } }"; // get a specific road by matching id
    String PROJECT_DIRECTIONS_2 = "{ '$project': { '_id': 0, 'direction': '$directions'} }";  // get all arrays of directions for each road
    String UNWIND_DIRECTIONS_2 = "{ '$unwind': { 'path': '$direction', preserveNullAndEmptyArrays: false} }"; // convert arrays of directions in objects
    String PROJECT_SECTIONS_1 = "{ '$project': {'section': '$direction.sections'} }";  // get all arrays of sections for each direction
    String UNWIND_SECTIONS_1 = "{ '$unwind': { 'path': '$section', preserveNullAndEmptyArrays: false} }"; // convert arrays of sections in objects
    String PROJECT_SECTIONS = "{ '$project': { 'uuid': '$section.uuid', 'name': '$section.name', 'description': '$section.description'} }"; // project the fields we want
    @Aggregation(pipeline = {MATCH_ROAD_BY_ID_1, PROJECT_DIRECTIONS_2, UNWIND_DIRECTIONS_2, PROJECT_SECTIONS_1, UNWIND_SECTIONS_1, PROJECT_SECTIONS})
    List<GeoDetails> getSectionsForARoad(String id);


    // Project GeoDetails obj for sections (get an obj for a specific road and a specific direction)
    String MATCH_ROAD_BY_ID_2 = "{ '$match': { 'uuid' : {'$eq': '?0'} } }"; // get a specific road by matching id
    String PROJECT_DIRECTIONS_3 = "{ '$project': { '_id': 0, 'direction': '$directions'} }";  // get all arrays of directions for each road
    String UNWIND_DIRECTIONS_3 = "{ '$unwind': { 'path': '$direction', preserveNullAndEmptyArrays: false} }"; // convert arrays of directions in objects
    String MATCH_DIRECTION_BY_ID = "{ '$match': { 'direction.uuid' : {'$eq': '?1'} } }"; // get a specific direction by matching id
    String PROJECT_SECTIONS_2 = "{ '$project': {'section': '$direction.sections'} }";  // get all arrays of sections for each direction
    String UNWIND_SECTIONS_2 = "{ '$unwind': { 'path': '$section', preserveNullAndEmptyArrays: false} }"; // convert arrays of sections in objects
    String PROJECT_SECTIONS_OBJ_2 = "{ '$project': { 'uuid': '$section.uuid', 'name': '$section.name', 'description': '$section.description'} }"; // project the fields we want
    @Aggregation(pipeline = {MATCH_ROAD_BY_ID_2, PROJECT_DIRECTIONS_3, UNWIND_DIRECTIONS_3, MATCH_DIRECTION_BY_ID, PROJECT_SECTIONS_2, UNWIND_SECTIONS_2, PROJECT_SECTIONS_OBJ_2})
    List<GeoDetails> getSectionForARoadAndDirection(String rId, String dId);





}