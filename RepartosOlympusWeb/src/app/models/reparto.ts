import { Timestamp } from "@angular/fire/firestore";
import { Cliente } from "./cliente";
import { ItemReparto } from "../components/agregar-reparto/agregar-reparto.component";

export interface Reparto {
    id?: number;
    anotacion?: string;
    clave?: string;
    estado?: string;
    fecha_creacion?: string;
    fecha_entrega?: string;
    id_cliente?: number;
    cliente?: Cliente;
    id_usuario?: number;
    usuario?: any;
    id_repartidor?: number;
    repartidor?: any;
    items?: ItemReparto[];
    total?: number;
}
