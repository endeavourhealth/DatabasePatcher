import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";
import {PatchEditDialog} from "./patchEditor.dialog";


@NgModule({
	imports : [
		BrowserModule,
		FormsModule,
	],
	declarations : [
		PatchEditDialog
	],
	entryComponents : [
		PatchEditDialog
	]
})
export class PatchModule {}