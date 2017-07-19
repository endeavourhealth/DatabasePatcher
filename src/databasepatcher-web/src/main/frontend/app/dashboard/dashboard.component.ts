import {Component} from "@angular/core";
import {StateService} from "ui-router-ng2";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {DashboardService} from "./dashboard.service";
import {LoggerService} from "eds-common-js";
import {Database} from "./models/Database";
import {DatabaseEditDialog} from "../database/databaseEditor.dialog";

@Component({
	template : require('./dashboard.html')
})
export class DashboardComponent {
	databases:Database[];
	regions = <any>[];
	zones = <any>[];
	dataTypes = <any>[];
	selectedRegion = '0';
	selectedZone = '0';
	selectedType = '0';

	constructor(private dashboardService:DashboardService,
							private $modal: NgbModal,
							private logger:LoggerService,
							private $state : StateService) {
		this.refresh();
		this.init();
	}

	refresh() {
		this.getDatabases();

	}

	addDatabase() {
		let vm = this;
		let database: Database = {
				name: "",
				hostname: "",
				port: "",
				username: "",
				password: "",
				lastPatchTimestamp: "",
				lastStatus: "",
				currentVersion: "",
				nextPatchTimestamp: "",
				targetVersion: "",
				region: "",
				zone: "",
				type: "",
				regionId: "",
				availabilityZoneId: "",
				dataTypeId: "",
				id: "",
				patchId: "",
				runNow: ""
			};

		DatabaseEditDialog.open(vm.$modal, database)
			.result.then(function (resultData: Database) {

			console.log(resultData);

			vm.dashboardService.saveDatabase(resultData)
				.subscribe(
					(data) => {
						vm.refresh();
					});
		});
	}

	editDatabase(db: Database) {
		let vm = this;

		DatabaseEditDialog.open(vm.$modal, db)
			.result.then(function (resultData: Database) {

			console.log(resultData);

			vm.dashboardService.saveDatabase(resultData)
				.subscribe(
					(data) => {
						vm.refresh();
					});
		});
	}

	init() {
		var vm = this;

		vm.dashboardService.getRegions()
			.subscribe(
				(data) => {
					vm.regions = data;
				});
		vm.dashboardService.getZones()
			.subscribe(
				(data) => {
					vm.zones = data;
				});
		vm.dashboardService.getDataTypes()
			.subscribe(
				(data) => {
					vm.dataTypes = data;
				});
	}

	getDatabases() {
		var vm:DashboardComponent = this;
		vm.databases = null;
		var region = vm.selectedRegion;
		var zone = vm.selectedZone;
		var type = vm.selectedType;

		vm.dashboardService.getDatabases(region, zone, type)
			.subscribe(
				(data:Database[]) => vm.databases = data
			);
	}


}

