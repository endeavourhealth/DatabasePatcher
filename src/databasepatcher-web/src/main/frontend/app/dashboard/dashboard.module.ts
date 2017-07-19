import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {DashboardService} from "./dashboard.service";
import {DashboardComponent} from "./dashboard.component";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {DialogsModule, LoggerModule} from "eds-common-js";
import {DatabaseModule} from "../database/database.module";

@NgModule({
	imports : [
		BrowserModule,
		FormsModule,
		NgbModule,
		LoggerModule,
		DialogsModule,
		DatabaseModule
	],
	declarations : [
		DashboardComponent
	],
	providers : [
		DashboardService
	]
})
export class DashboardModule {}