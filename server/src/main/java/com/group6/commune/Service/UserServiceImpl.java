package com.group6.commune.Service;

import com.group6.commune.Model.EmailDetails;
import com.group6.commune.Model.User;
import com.group6.commune.Repository.EmailTemplateRepositoryImpl;
import com.group6.commune.Repository.UserRepository;
import com.group6.commune.Utils.CommuneEmailAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailTemplateRepositoryImpl emailTemplateRepo;

    @Autowired
    CommuneEmailAgent mailAgent;

    @Override
    public User getUserDetailsById(int userId){
        return userRepository.getUserDetailsByID(userId);
    }

    @Override
    public Boolean createUser(User user){
        Boolean status= userRepository.createUserAccount(user);
        if(status==true)
        {
            EmailDetails emailDetails=emailTemplateRepo.getEmailDetailsFromDB(1);
            emailDetails.setRecipient(user.getEmail());
            mailAgent.sendSimpleMail(emailDetails) ;
        }
        return status;
    }

    @Override
    public Boolean updateAccountDetails(User user)
    {
        return userRepository.updateAccountDetails(user);
    }

    @Override
    public Boolean deleteUserAccountById(int id)
    {
        return userRepository.deleteUserAccountById(id);
    }

    @Override
    public int createVerificationCode(String email){
        EmailDetails emailDetails=emailTemplateRepo.getEmailDetailsFromDB(2);
        emailDetails.setRecipient(email);
        int verificationCode=new Random().nextInt(900000) + 100000;
        emailDetails.setMailBody(emailDetails.getMailBody()+verificationCode+"<br><br>Thanks and Regards,<br>Commune Team");
        mailAgent.sendSimpleMail(emailDetails) ;
        return verificationCode;
    }

    @Override
    public Boolean updateUserPassword(User user)
    {
        return userRepository.updatePassword(user);
    }


}
