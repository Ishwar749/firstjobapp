package com.ishwar.firstjobapp.job.Impl;

import com.ishwar.firstjobapp.job.Job;
import com.ishwar.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        for(Job job:  jobs){
            if(job.getId().equals(id)){
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean deleteJob(Long id) {

        int indexToDelete = -1;

        for(int i = 0; i<jobs.size(); i++){
            Job currentJob = jobs.get(i);
            if(currentJob.getId().equals(id)){
                indexToDelete = i;
                break;
            }
        }

        if(indexToDelete==-1) return false;

        jobs.remove(indexToDelete);
        return true;
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        boolean jobUpdated = false;

        for(Job job: jobs){
            if(job.getId().equals(id)){
                setJobDetails(job,updatedJob);
                jobUpdated = true;
                break;
            }
        }

        if(jobUpdated) return true;
        return false;
    }

    public void setJobDetails(Job oldJob, Job updatedJob){
        oldJob.setId(updatedJob.getId());
        oldJob.setDescription(updatedJob.getDescription());
        oldJob.setTitle(updatedJob.getTitle());
        oldJob.setLocation(updatedJob.getLocation());
        oldJob.setMinSalary(updatedJob.getMinSalary());
        oldJob.setMaxSalary(updatedJob.getMaxSalary());
    }
}
