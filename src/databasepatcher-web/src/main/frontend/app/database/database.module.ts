import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {DatabaseEditDialog} from "./databaseEditor.dialog";


@NgModule({
	imports : [
		BrowserModule,
		FormsModule,
	],
	declarations : [
		DatabaseEditDialog
	],
	entryComponents : [
		DatabaseEditDialog
	]
})
export class DatabaseModule {}