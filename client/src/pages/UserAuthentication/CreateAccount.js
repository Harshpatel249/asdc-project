import React, { useState } from 'react';
import './login.css';
import './App.css';
import PhoneInput from 'react-phone-input-2'

export function CreateAccount() {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [dateOfBirth, setDateOfBirth] = useState('');
  const [gender, setGender] = useState('Male');
  const [email, setEmail] = useState('');
  const [contact, setContact] = useState('');
  const [password, setPassword] = useState('');
  const [DobErrorMsg, setDobErrorMsg] = useState('');
  const [error, setError] = useState(false);
  const [passErrorMsg, setPassErrorMsg] = useState('');
  const [emailErrorMsg, setEmailErrorMsg] = useState('');
  const regEx = /[a-zA-Z0-9._-]+@[a-z0-9]+\.[a-z]{2,8}(.[a-z{2,8}])?/g; 
  const passwordRegex=/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]{8,}$/;

  const isDateValid = (dateString) => {
    const dateObject = new Date(dateString);
    const currentDate = new Date();

    if (dateObject.toString() === 'Invalid Date') {
      return false;
    }
    const year = dateObject.getFullYear();
    const currentYear = currentDate.getFullYear();
    if (year < 1900 || year > currentYear) {
      return false;
    }
    return true;
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

  const handleCreateAccount = () => {
    if(firstName==="" || firstName===null|| lastName==="" || lastName===null )
    {
        alert('First or Last Name cannot be empty');
    }
    else if(isDateValid(dateOfBirth)!==true)
    {
        setDateOfBirth("");
        alert('Invalid Date of birth');
    }
    else if(contact==="" || contact===null)
    {
        alert('Please Enter Contact Details');
    }
    else if(error===true || email===""|| password==="")
    alert("Please provide valid inputs")
    else{
    alert('Account Created Successfully!');
    setFirstName('');
    setLastName('');
    setDateOfBirth('');
    setGender('Male');
    setEmail('');
    setContact('');
    setPassword('');
    }
  };

  return (
    <div className="App">
      <header className="App-header">
        <div className="loginForm">
           
          <h3 style={{ marginRight: '200px'}}>Create An Account !</h3>
          <div className="createAccount">
          <form>
            <input type="text" placeholder="First Name*" value={firstName} onChange={(e) => setFirstName(e.target.value)} />
            
            <input type="text" placeholder="Last Name*" value={lastName} onChange={(e) => setLastName(e.target.value)} />
    
            <input type="date" placeholder="Date of Birth" value={dateOfBirth} onChange={(e) => setDateOfBirth(e.target.value)}/>
            
            <div className="genderSection">
                <label>Gender:</label>
                <div className="genderOptions">
                  <input
                    type="radio"
                    value="Male"
                    checked={gender === 'Male'}
                    onChange={() => setGender('Male')}
                  />
                  <span>Male</span>
                </div>
                <div className="genderOptions">
                  <input
                    type="radio"
                    value="Female"
                    checked={gender === 'Female'}
                    onChange={() => setGender('Female')}
                  />
                  <span>Female</span>
                </div>
                <div className="genderOptions">
                  <input
                    type="radio"
                    value="Other"
                    checked={gender === 'Other'}
                    onChange={() => setGender('Other')}
                  />
                  <span>Other</span>
                </div>
              </div>

            <input type="email"  placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)}  onBlur={onEmailBlur} />
            <i dangerouslySetInnerHTML={{ __html: emailErrorMsg }} />

            <PhoneInput country={'canada'} placeholder='Mobile N0' value={contact} onChange={setContact}  />
            
            <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)}  onBlur={onPasswordBlur} />
           <i dangerouslySetInnerHTML={{ __html: passErrorMsg }} />

            <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-around', marginTop: '20px' }} >
              <button type="button" onClick={handleCreateAccount}>
                Create Account
              </button><a href="/"  style={{ marginLeft: '100px'}} >
              Go Back to login
              </a>
            </div>
          </form>
          </div>
        </div>
      </header>
    </div>
  );
}
