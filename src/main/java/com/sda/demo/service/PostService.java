package com.sda.demo.service;

import com.sda.demo.entity.Post;
import com.sda.demo.repository.PostRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PostService implements PostRepository {


    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public List<Post> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Post> findAll(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void delete(Post post) {

    }

    @Override
    public void delete(Iterable<? extends Post> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Post> S save(S s) {
        return null;
    }

    @Override
    public <S extends Post> List<S> save(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Post findOne(Long aLong) {
        return null;
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Post> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Post> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Post getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Post> S findOne(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Post> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Post> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Post> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Post> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Post> boolean exists(Example<S> example) {
        return false;
    }


}
