
export function GetGames(console) {
 return fetch('/v1/games?console=' + console, { headers: { "Accept": "application/vnd.api+json" }})
    .then(response => response.json());
}

export function GetUser() {
 return fetch('/v1/games?console=' + console, { headers: { "Accept": "application/vnd.api+json" }})
    .then(response => response.json());
}