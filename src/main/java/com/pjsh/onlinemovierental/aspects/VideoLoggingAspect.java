package com.pjsh.onlinemovierental.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class VideoLoggingAspect {
    @Before("execution(* com.pjsh.onlinemovierental.services.VideoService.addMovie(..))")
    public void logBeforeAddMovie() {
        System.out.println("< ADDING MOVIE >");
    }

    @Before("execution(* com.pjsh.onlinemovierental.services.VideoService.addTVShow(..))")
    public void logBeforeAddTVShow() {
        System.out.println("< ADDING NEW TV SHOW SEASON >");
    }

    @Before("execution(* com.pjsh.onlinemovierental.services.VideoService.removeVideo(..))")
    public void logBeforeRemoveVideo() {
        System.out.println("< REMOVING VIDEO >");
    }

    @Before("execution(* com.pjsh.onlinemovierental.services.VideoService.viewAllVideos(..))")
    public void logBeforeViewAllMovies() {
        System.out.println("< GATHERING ALL VIDEO DATA >");
    }

    @Before("execution(* com.pjsh.onlinemovierental.services.VideoService.findVideoByTitle(..))")
    public void logBeforeFindVideoByTitle() {
        System.out.println("< SEARCHING VIDEOS BY TITLE >");
    }

    @Before("execution(* com.pjsh.onlinemovierental.services.VideoService.findVideoByGenre(..))")
    public void logBeforeFindVideoByGenre() {
        System.out.println("< SEARCHING VIDEOS BY GENRE >");
    }

    @Before("execution(* com.pjsh.onlinemovierental.services.VideoService.findVideoByGenre(..))")
    public void logBeforeFindVideoByReleaseYear() {
        System.out.println("< SEARCHING VIDEOS BY RELEASE YEAR >");
    }
}
