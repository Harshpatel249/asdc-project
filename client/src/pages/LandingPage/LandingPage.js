import React from 'react';
import { NavLink } from 'react-router-dom';

function LandingPage() {
    return (
        <>
            <p>Landing Page!</p>
            <NavLink to="/onboarding">
                <button>
                    Onboarding
                </button>
            </NavLink>
            <NavLink to="/user/login">
                <button>
                    login
                </button>
            </NavLink>
            <NavLink to="/create-community">
                <button>
                    Create community
                </button>
            </NavLink>

        </>
    );
}

export default LandingPage;