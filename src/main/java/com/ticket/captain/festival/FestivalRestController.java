package com.ticket.captain.festival;

import com.ticket.captain.error.NotFoundException;
import com.ticket.captain.util.ApiResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FestivalRestController {

    private final FestivalService festivalService;

    public FestivalRestController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    @PostMapping("/manager/generate")
    public ApiResult<FestivalDto> generate(@RequestBody FestivalRequest request) {

        return ApiResult.OK(
            new FestivalDto(
                    festivalService.generate(
                            new Festival(request.getName(), request.getContent(), request.getWinners(),
                            request.getThumbnail(), request.getStartDate(), request.getEndDate())
                    ))
        );
    }

    @GetMapping("/manager/{festivalId}/info")
    public ApiResult<FestivalDto> festivalInfo(@PathVariable Long festivalId) {
        return ApiResult.OK(
                festivalService.findById(festivalId)
                .map(FestivalDto::new)
                .orElseThrow(() -> new NotFoundException(Festival.class, festivalId))
        );
    }

}
