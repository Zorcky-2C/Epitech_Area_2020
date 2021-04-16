import React, {useState, useEffect} from 'react'
function Cinema() {
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    useEffect(() => {
        fetch("http://10.0.2.2:8080/accounts/cinema")
        .then(res => res.json())
        .then(
            (result) => {
                setIsLoaded(true);
                setItems(result);
            },
            (error) => {
                setIsLoaded(true);
                setError(error);
            }
        )
    }, [])
    if (error) {
        return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
        return <div>Loading...</div>;
    } else {

        return (
            <ul>
                {items.map(item => (
                    <li key={item.id}>
                        {item.mail} {item.password}
                    </li>
                ))}
            </ul>
        );
    }
}

export default Cinema
