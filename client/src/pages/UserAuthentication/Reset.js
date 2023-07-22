import React, { useState } from 'react';
import './login.css';
import './App.css'
export function Reset() {
  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [verificationCode, setVerificationCode] = useState('');
  const [error, setError] = useState(false);
  const [passErrorMsg, setPassErrorMsg] = useState('');
  const [conPassErrorMsg, setconPassErrorMsg] = useState('');
  const passwordRegex=/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]{8,}$/;
  const onNewPasswordBlur = () => {
    if(!passwordRegex.test(newPassword) && newPassword!=="")
    {
      setError(true);
      setPassErrorMsg("Password must contain 1 uppercase<br>Must contain lowercase <br> Must contain special charcter <br> minimum length of 8 characters");
    }
    else
    {
      setError(false);
      setPassErrorMsg('');
    }
  };
  const onconfirmPasswordBlur = () => {
    if(!passwordRegex.test(confirmPassword) && confirmPassword!=="")
    {
      setError(true);
      setconPassErrorMsg("Password must contain 1 uppercase<br>Must contain lowercase <br> Must contain special charcter <br> minimum length of 8 characters");
    }
    else
    {
      setError(false);
      setconPassErrorMsg('');
    }
  };
  const onSubmit = () => {
    if(error===true || newPassword===""|| confirmPassword==="")
    alert("Please enter the correct password");
    else if(newPassword!==confirmPassword)
    alert("Confirm password does not match with new password ");
    else if(verificationCode.length!==6)
    alert("please enter the valid 6 letter alphanumeric code");
    else
    {
    alert("Password Changed successfully\n\n"+
    "redirecting to the login page......");
    setNewPassword('');
    setConfirmPassword('');
    setVerificationCode('');
    window.location.href="/";
    } 
  };
  const resendCode = () => {
    alert("Code has been sent on registered Email ID");
  };
    return (
      <div className="App">
        <header className="App-header">
        <div className='loginForm'>
        
        <form>
        <h3 text>Reset Your Password</h3>
            <input type="password" placeholder='New Password' value={newPassword} onChange={(e) => setNewPassword(e.target.value)} onBlur={onNewPasswordBlur}/>
            <i dangerouslySetInnerHTML={{ __html: passErrorMsg }} />
          <div>
            <input type="password" placeholder='Confirm Password' value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)}  onBlur={onconfirmPasswordBlur} />
            <i dangerouslySetInnerHTML={{ __html: conPassErrorMsg }} />
          </div>
          <div style={{ display: 'flex', alignItems: 'center', justifyContent:"space-around" }}>
          <input type="text" placeholder='Verification code' value={verificationCode} onChange={(e) => setVerificationCode(e.target.value) } />
            <a href="#" onClick={resendCode}  style={{ marginLeft: '10px' }} >
              Resend Code
            </a>
            </div>
            <div style={{ display: 'flex', alignItems: 'center', justifyContent:"space-around" }}>
            <button type="button" onClick={onSubmit}>Submit</button><a href="/"  style={{ marginLeft: '10px', marginTop: '10px'}} >
              Go Back to login
              </a>
            </div>
            
        </form>
      </div>
        </header>
      </div>
    );
  }