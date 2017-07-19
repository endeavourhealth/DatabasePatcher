import {Injectable} from "@angular/core";
import {Http, URLSearchParams} from "@angular/http";
import {Observable} from "rxjs";
import {BaseHttp2Service} from "eds-common-js";
import {Database} from "./models/Database";
import {Region} from "./models/Region";
import {Zone} from "./models/Zone";
import {DataType} from "./models/DataType";

@Injectable()
export class DashboardService extends BaseHttp2Service {
	constructor(http : Http) { super(http); }

	getDatabases(region: string, zone: string, type: string):Observable<Database[]> {
		var params : URLSearchParams = new URLSearchParams();
		params.append('region', region);
		params.append('zone', zone);
		params.append('type', type);

		return this.httpGet('api/dashboard/getDatabases', { search : params });
	}

	getRegions():Observable<Region> {
		return this.httpGet('api/dashboard/getRegions');
	}

	getZones():Observable<Zone> {
		return this.httpGet('api/dashboard/getZones');
	}

	getDataTypes():Observable<DataType> {
		return this.httpGet('api/dashboard/getDataTypes');
	}

	saveDatabase(result : Database):Observable<Database> {
		return this.httpPost('api/dashboard/saveDatabase', result);
	}



}
