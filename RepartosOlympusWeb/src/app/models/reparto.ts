import { Timestamp } from "@angular/fire/firestore";
import { Cliente } from "./cliente";
import { ItemReparto } from "../components/agregar-reparto/agregar-reparto.component";

export interface Reparto {
    id?: string;
    anotacion?: string;
    clave?: string;
    estado?: string;
    fecha?: Timestamp;
    idCliente?: string;
    cliente?: Cliente;
    items?: ItemReparto[]
}
