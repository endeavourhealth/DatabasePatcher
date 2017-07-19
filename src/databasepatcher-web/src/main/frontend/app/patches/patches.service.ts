import {Injectable} from "@angular/core";
import {Http, URLSearchParams} from "@angular/http";
import {Observable} from "rxjs";
import {BaseHttp2Service} from "eds-common-js";
import {Patch} from "./models/Patch";

@Injectable()
export class PatchesService extends BaseHttp2Service {
	constructor(http : Http) { super(http); }

	getPatches():Observable<Patch[]> {
		var params : URLSearchParams = new URLSearchParams();

		return this.httpGet('api/patch/getPatches', { search : params });
	}

	savePatch(result : Patch):Observable<Patch> {
		return this.httpPost('api/patch/savePatch', result);
	}




}
