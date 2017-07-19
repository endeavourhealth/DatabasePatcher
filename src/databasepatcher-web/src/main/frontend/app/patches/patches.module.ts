import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {PatchesService} from "./patches.service";
import {PatchesComponent} from "./patches.component";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {DialogsModule, LoggerModule} from "eds-common-js";
import {PatchModule} from "../patch/patch.module";

@NgModule({
	imports : [
		BrowserModule,
		FormsModule,
		NgbModule,
		LoggerModule,
		DialogsModule,
		PatchModule

	],
	declarations : [
		PatchesComponent
	],
	providers : [
		PatchesService
	]
})
export class PatchesModule {}