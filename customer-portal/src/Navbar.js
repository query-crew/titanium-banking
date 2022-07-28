import './Navbar.css';

const Navbar = () => {

    return(
        <>
        <nav className="navbar navbar navbar-expand-sm navbar-light ">
            <div className="container-fluid">
                
                <div className="collapse navbar-collapse" id="navbarNav">
                <ul className="navbar-nav">
                    <li className="nav-item">
                    <a className="nav-link" aria-current="page" href="#">Contacts</a>
                    </li>
                    <li className="nav-item">
                    <a className="nav-link" href="#">ATMs/Locations</a>
                    </li>
                    <li className="nav-item">
                    <a className="nav-link" href="#">Credit Cards</a>
                    </li>
                    <li className="nav-item">
                    <a className="nav-link" href="#" >Loans</a>
                    </li>
                    <li className="nav-item">
                    <a className="nav-link" href="#" >Live Chat</a>
                    </li>
                </ul>
                </div>
            </div>
        </nav>
        </>
    );
}

export default Navbar;