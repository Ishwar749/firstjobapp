package com.ishwar.firstjobapp.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long companyId, Review review);

    Review getReviewById(Long companyId, Long reviewId);
}
