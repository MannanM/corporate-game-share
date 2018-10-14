export function WhoAmI() { return get('/v1/users/me'); }
export function GetConsoles() { return get('/v1/consoles'); }
export function GetUser(id) { return get('/v1/users/' + id); }
export function GetLibrary(id) { return get('/v1/users/' + id + '/games'); }
export function GetGame(id) { return get('/v1/games/' + id); }
export function GetGames(console) { return get('/v1/games?console=' + console); }

export function AddToLibrary(id) { return send('/v1/users/games', {attributes: {game: {id: id}}}); }

export function send(uri, data = {}, method = 'POST') {
    return get(uri, {
        method: method,
        cache: "no-cache",
        headers: {
            "Content-Type": "application/vnd.api+json"
        },
        body: JSON.stringify(data)
    });
}

export default function get(uri, options = {}) {
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