import React, { useEffect, useState } from 'react';
import './Onboarding.css';
import image from '../../Assets/Images/category.jpg';

function Onboarding() {
  const [interestList, setInterestList] = useState([]);
  const [checkedInterests, setCheckedInterests] = useState({});

  useEffect(() => {
    const getOptions = {
      method: 'GET',
      headers: {
          'Content-Type': 'application/json',
          'Authorization': localStorage.getItem('BearerToken')
      }
  }
    fetch('https://commune-dev-csci5308-server.onrender.com/interest', getOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log(data);
        setInterestList(data);
        const initialCheckedInterests = {};
        data.forEach(interest => {
          initialCheckedInterests[interest.interestId] = false;
        });
        setCheckedInterests(initialCheckedInterests);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
  }, []);

  // Function to handle checkbox changes
  const handleCheckboxChange = (interestId) => {
    setCheckedInterests(prevCheckedInterests => ({
      ...prevCheckedInterests,
      [interestId]: !prevCheckedInterests[interestId]
    }));
  };

  // Function to handle the Save button click
  const handleSaveButtonClick = () => {
    const selectedInterests = Object.keys(checkedInterests).filter(
      (interestId) => checkedInterests[interestId]
    );

    const postData = {
      userId: 2,
      interestIds: selectedInterests
    };

    // Make the POST API call with the postData
    fetch('https://commune-dev-csci5308-server.onrender.com/interest', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': localStorage.getItem('BearerToken')
      },
      body: JSON.stringify(postData)
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        console.log('API response:', data);
        // setSuccessMessage('Interests added successfully!');
      })
      .catch(error => {
        console.error('Error sending data:', error);
      });
  };

  return (
    <>
      <div>
        <header className="bg-lightblue p-2">
          <h3>Select at least 3 categories.</h3>
        </header>
        <div className="container d-flex justify-content-start flex-wrap mt-2">
          {interestList.map((interest) => (
            <div className="card mb-3 me-2" key={interest.interestId} style={{ width: "24%" }}>
              <img src={image} alt={interest.interestName} />
              <div className="card-body">
                <div className="form-check">
                  <label className="form-check-label">{interest.interestName}</label>
                  <input
                    className="form-check-input"
                    type="checkbox"
                    style={{ width: "1.5rem", height: "1.5rem" }}
                    checked={checkedInterests[interest.interestId]}
                    onChange={() => handleCheckboxChange(interest.interestId)}
                  />
                </div>
              </div>
            </div>
          ))}
        </div>
        <button
          className="save-btn position-fixed bottom-0 end-0 m-4"
          onClick={handleSaveButtonClick}
        >
          Save
        </button>
      </div>
    </>
  );
}

export default Onboarding;