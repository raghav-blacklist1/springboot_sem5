package com.raghav.moviesnow.modeldao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.raghav.moviesnow.models.Profile;

public interface Profiledao extends CrudRepository<Profile, Integer> {

    @Query("SELECT CASE WHEN count(email) > 0 THEN true ELSE false END FROM Profile where email = :inp")
    public boolean userexists(@Param("inp") String inp);

    @Query("SELECT f_name FROM Profile where email = :inp")
    public String username(@Param("inp") String inp);

    @Query("SELECT p FROM Profile p where p.email = :inp")
    public Profile getprofile(@Param("inp") String inp);

    @Query("SELECT pass FROM Profile where email = :inp")
    public String getpass(@Param("inp") String inp);

    @Query("SELECT priv_lvl FROM Profile where email = :inp")
    public int getpriv(@Param("inp") String inp);

    @Query("SELECT p FROM Profile p")
    public List<Profile> getall();

    @Transactional
    @Modifying
    @Query("UPDATE Profile set priv_lvl=2 where email= :inp")
    public void elevate_priv(@Param("inp") String inp);

    @Transactional
    @Modifying
    @Query("UPDATE Profile set priv_lvl=1 where email= :inp")
    public void revoke_priv(@Param("inp") String inp);

    @Query("SELECT p FROM Profile p where id = :inp")
    public Profile getbyid(@Param("inp") int inp);
}