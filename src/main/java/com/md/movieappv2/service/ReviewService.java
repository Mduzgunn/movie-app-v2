package com.md.movieappv2.service;

import com.md.movieappv2.dto.DirectorDto;
import com.md.movieappv2.dto.ReviewDto;
import com.md.movieappv2.exception.DirectorNotFoundException;
import com.md.movieappv2.model.Director;
import com.md.movieappv2.model.Review;
import com.md.movieappv2.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    protected Review findReviewById(String id) {
        return reviewRepository
                .findById(id)
                .orElseThrow(() -> new DirectorNotFoundException("review not found " + id));
    }

    protected List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

//    public ReviewDto getReviewById(String id) {
//        return reviewDtoConverter.convert(findReviewById(id));
//    }
//
//    public List<ReviewDto> getAllReviewDtoList() {
//        return convertToDirectorDtoList(getAllDirectors());
//    }
}
