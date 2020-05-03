__config() -> m(
	l('stay_loaded', true),
	l('scope', 'global')
);

__on_tick() -> _tick();
__on_tick_nether() -> _tick();
__on_tick_ender() -> _tick();

texture_base64 = 'eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWNjYzc5ZmU5OWY0MzMxMDFkOTJmMzQ1ZmIyMjM5ZGIzMGQwNzM5Y2NjYTkzMzlmYTI2MjNlYjZiMDZhNTgzYSJ9fX0=';

below(pos) -> l(pos:0, pos:1 - 1, pos:2);

spawn_tiny_potato(pos, count) -> spawn('item', pos, '{Item:{
	id:"player_head",
	Count:'+count+',
	tag:{
		display:{Name:"{\\"text\\":\\"Tiny Potato\\"}"},
		SkullOwner:{
			Id:"9da44549-cde5-4748-8341-2a3899087a9f",
			Properties:{textures:[{Value:"'+texture_base64+'"}]}
		}
	}
}}');

cauldron_craft_tiny_potato() -> for(
	filter(
		entity_list('items'), true
		&& _~'item':0 == 'potato'
		&& block(_~'pos') == 'cauldron'
		&& property(_~'pos','level') > 0
		&& block(below(_~'pos')) == 'fire'
		// TODO: make this only work for soul fire.
	),(
		spawn_tiny_potato(_~'pos',_~'item':1);
		modify(_,'remove');
	)
);

fix_tiny_potato_names() -> for(
	filter(
		entity_list('items'), true
		&& _~'item':0 == 'player head'
		&& false
		// TODO: We need to assign some sort of internal tag to tiny
		// potato so we can search for it here.
	),(
		// TODO: Change name from 'player head' back to 'tiny potato'.
	)
);

_tick() -> (
	cauldron_craft_tiny_potato();
	// fix_tiny_potato_names();
);
