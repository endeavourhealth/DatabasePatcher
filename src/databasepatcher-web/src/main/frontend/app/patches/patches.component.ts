import {Component} from "@angular/core";
import {StateService} from "ui-router-ng2";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {PatchesService} from "./patches.service";
import {LoggerService} from "eds-common-js";
import {Patch} from "./models/Patch";
import {PatchEditDialog} from "../patch/patchEditor.dialog";

@Component({
	template : require('./patches.html')
})
export class PatchesComponent {
	patches:Patch[];

	constructor(private patchesService:PatchesService,
							private $modal: NgbModal,
							private logger:LoggerService,
							private $state : StateService) {
		this.refresh();
		this.init();
	}

	refresh() {
		this.getPatches();

	}

	addPatch() {
		let vm = this;
		let patch: Patch = {
				id: "",
				patchDescription: "",
				createdTimestamp: "",
				patchScript: ""
			};

		PatchEditDialog.open(vm.$modal, patch)
			.result.then(function (resultData: Patch) {

			console.log(resultData);

			vm.patchesService.savePatch(resultData)
				.subscribe(
					(data) => {
						vm.refresh();
					});
		});
	}

	editPatch(patch: Patch) {
		let vm = this;

		PatchEditDialog.open(vm.$modal, patch)
			.result.then(function (resultData: Patch) {

			console.log(resultData);

			vm.patchesService.savePatch(resultData)
				.subscribe(
					(data) => {
						vm.refresh();
					});
		});
	}

	init() {
		var vm = this;

	}

	getPatches() {
		var vm:PatchesComponent = this;
		vm.patches = null;

		vm.patchesService.getPatches()
			.subscribe(
				(data:Patch[]) => vm.patches = data
			);
	}


}

