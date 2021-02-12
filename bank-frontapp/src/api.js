import axios from "axios"

export const CLIENT_SERVICE = "CLIENT-SERVICE";
export const COMPTEOPS_SERVICE = "COMPTEOPS-SERVICE";
export default axios.create({
    baseURL:"localhost:8080"
})