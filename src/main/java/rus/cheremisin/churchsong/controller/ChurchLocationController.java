package rus.cheremisin.churchsong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import rus.cheremisin.churchsong.DTO.church.ChurchLocationDTO;
import rus.cheremisin.churchsong.service.church.ChurchLocationService;

import java.util.List;

//@RestController
public class ChurchLocationController {
    private ChurchLocationService churchLocationService;

    @Autowired
    public ChurchLocationController(ChurchLocationService churchLocationService) {
        this.churchLocationService = churchLocationService;
    }

    @GetMapping("/fetchLocations")
    public ResponseEntity fetchAndSaveLocations() {
        List<ChurchLocationDTO> response = churchLocationService.fetchAndSaveLocations();

        if (response == null) {
            return ResponseEntity.internalServerError().body("no data came from locations API");
        }
        return ResponseEntity.ok(response);
    }
}
