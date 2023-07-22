import React, { useState } from 'react';
import './login.css';
import './App.css'
export function Login (){

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(false);
    const [passErrorMsg, setPassErrorMsg] = useState('');
    const [emailErrorMsg, setEmailErrorMsg] = useState('');
    const regEx = /[a-zA-Z0-9._-]+@[a-z0-9]+\.[a-z]{2,8}(.[a-z{2,8}])?/g; 
    const passwordRegex=/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]{8,}$/;
    const onLogin = () => {
      
      if(error===true || email===""|| password==="")
      alert("Please enter the valid credentials")
      else
      {
      alert("Welcome, You have successfully logged in");
      setEmail('');
      setPassword('');
      window.location.href="/home";
      }
      
    };

    const onEmailBlur =() => {
      if(!regEx.test(email) && email!=="")
      {
              setError(true);
              setEmailErrorMsg("Please enter a valid email address");
      }
      else
      {
      setError(true);
      setEmailErrorMsg("");
      }

    }

    const onPasswordBlur = () => {
      if(!passwordRegex.test(password) && password!=="")
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

    return (
      <div className="App">
        <header className="App-header">
        <div className='loginForm'>
        <h1>Login Screen</h1>
        <form>
          <div style={{display:"flex",justifyContent:"center"}}>
  
          <img src="loginimg.png" alt='loginImage' width="100" height="100" />
          </div>
            <input type="email" placeholder='Enter Email' value={email} onChange={(e) => setEmail(e.target.value)} onBlur={onEmailBlur} />
            <i dangerouslySetInnerHTML={{ __html: emailErrorMsg }} />
          <div>
            <input type="password" placeholder='Enter Password' value={password} onChange={(e) => setPassword(e.target.value)} onBlur={onPasswordBlur} />
            <i dangerouslySetInnerHTML={{ __html: passErrorMsg }} />
          </div>
          
          <div style={{ display: 'flex', alignItems: 'center', justifyContent:"space-around" }}>
            <button type="button" onClick={onLogin}>Login</button>
            
            </div>
            <a href="/signup"  style={{ marginLeft: '10px' }} >
              Create an account
              </a>
              <a href="/reset"  style={{ marginLeft: '10px' }} >
              Forgot Password?
            </a>
            
        </form>
      </div>
        </header>
      </div>
    );  
}