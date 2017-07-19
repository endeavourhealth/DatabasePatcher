// Styling
import '../content/css/index.css';

// Core
import {NgModule} from '@angular/core';

// Modules
import {DashboardModule} from "./dashboard/dashboard.module";
import {PatchesModule} from "./patches/patches.module";

// State components
import {DashboardComponent} from "./dashboard/dashboard.component";
import {PatchesComponent} from "./patches/patches.component";
import {Application} from "eds-common-js";
import {DatabasePatcherMenuService} from "./databasePatcher.menu";

@NgModule(
	Application.Define({
		modules: [
			DashboardModule,
			PatchesModule
		],
		states: [
			{ name: 'app.dashboard', url: '/dashboard', component: DashboardComponent },
			{ name: 'app.patches', url: '/patches', component: PatchesComponent }
		],
		defaultState : { state: 'app.dashboard', params: {} },
		menuManager : DatabasePatcherMenuService
	})
)
export class AppModule {}

Application.Run(AppModule);