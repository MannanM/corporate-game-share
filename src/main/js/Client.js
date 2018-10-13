
export function GetGames(console) {
 return fetch('/v1/games?console=' + console, { headers: { "Accept": "application/vnd.api+json" }})
    .then(response => response.json());
}

export function WhoAmI() {
 return api('/v1/users/me');
}

export function GetUser(id) {
 return api('/v1/users/' + id);
}

export function GetLibrary(id) {
 return api('/v1/users/' + id + '/games');
}

export function GetGame(id) {
 return api('/v1/games/' + id);
}

export function AddToLibrary(id) {
 return send('/v1/users/games', {attributes: {game: {id: id}}});
}

export function send(uri, data = {}, method = 'POST') {
    return api(uri, {
        method: method,
        cache: "no-cache",
        headers: {
            "Content-Type": "application/vnd.api+json"
        },
        body: JSON.stringify(data)
    });
}

export default function api(uri, options = {}) {
  options.headers = Object.assign({
    'Accept': 'application/vnd.api+json'
  }, options.headers);
  return new Promise((resolve, reject) => {
    fetch(uri, options)
      .then(response => {
        if (response.ok) {
           return response.text().then(text => {
             resolve(text ? JSON.parse(text) : {});
           });
        }
        return response.json().then(json => {
          return reject({
            status: response.status,
            ok: false,
            statusText: response.statusText,
            body: json
          });
        });
      });
  });
}