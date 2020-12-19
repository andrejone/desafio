import { Country } from "./country";
import { Indicator } from "./indicator";

export class EconomicIndicator {
	countryiso3code : string;
	date : string;
	value : string;
    unit : string;
    obs_status : string;
    decimal : number;
    indicator: Indicator;
    country: Country;
}