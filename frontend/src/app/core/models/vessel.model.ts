export interface Vessel {
  id?: number;
  name: string;
  registrationNumber: string;
  state: string;
  classEntityId?: number | null;
}
