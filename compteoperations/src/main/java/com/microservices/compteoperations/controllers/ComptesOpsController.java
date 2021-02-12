package com.microservices.compteoperations.controllers;

import com.microservices.compteoperations.entities.Compte;
import com.microservices.compteoperations.entities.Operation;
import com.microservices.compteoperations.services.CompteOpsService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
public class ComptesOpsController {
    CompteOpsService compteOpsService;

    @PostMapping("comptes")
    Compte addCompte(@RequestBody() Compte comptePayload){
        return compteOpsService.addCompte(comptePayload);
    }

    @PostMapping("/operations/versement")
    Compte versement(@RequestParam("compteId") Long compteId,
                     @RequestParam("montant") double montant){
       return compteOpsService.verseMontantCompte(compteId, montant);
    }


    @PostMapping("/operations/retrait")
    Compte retrait(@RequestParam("compteId") Long compteId,
                     @RequestParam("montant") double montant){
        return compteOpsService.retraitMontantCompte(compteId, montant);
    }
    @PostMapping("/operations/virement")
    double virement(@RequestParam("compteEmetId") Long compteEmetId,
                   @RequestParam("compteDestId") Long compteDestId,
                   @RequestParam("montant") double montant){
        return compteOpsService.virementCompte(compteEmetId,compteDestId, montant);
    }

    @GetMapping("/comptes/{id}/operations")
    Collection<Operation> compteOperations(@PathVariable("id") Long compteId
                                   ){
        return compteOpsService.listOperations(compteId);
    }

    @GetMapping("/comptes/{id}/clients")
    Compte compteclient(@PathVariable("id") Long compteId
    ){
        System.out.println(compteId);
       return compteOpsService.getCompteetClient(compteId);
    }

    @PostMapping("/comptes/{id}/etat")
    Compte editEtatCompte(@PathVariable("id") Long compteId, @RequestParam("etat") boolean etat
    ){
        return compteOpsService.editCompteEtat(compteId, etat);
    }

}
