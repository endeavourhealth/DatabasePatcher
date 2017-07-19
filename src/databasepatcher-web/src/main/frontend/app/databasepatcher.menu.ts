import {Injectable} from "@angular/core";
import {MenuService} from "eds-common-js";
import {MenuOption} from "eds-common-js/dist/layout/models/MenuOption";

@Injectable()
export class DatabasePatcherMenuService implements  MenuService {
	getApplicationTitle(): string {
		return 'Database Patcher';
	}
	getMenuOptions():MenuOption[] {
		return [
			{caption: 'Dashboard', state: 'app.dashboard', icon: 'fa fa-tachometer'},
			{caption: 'Patches', state: 'app.patches', icon: 'fa fa-wrench'}
		];
	}
}